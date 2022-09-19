# [BE] airbnb

airbnb 의 일부 기능을 구현하는 프로젝트로, 다음과 같은 기능들을 구현하는것을 목표로 합니다.

- 숙소
  - 숙소 가격 통계 조회기능
  - 예약 가능한 숙소 조회 기능
  - 숙소의 위치 정보 제공
    - 중심 좌표 기준, 일정 거리내 있는 숙소들의 정보 제공
- 예약
  - 선택한 숙소를 예약하는 기능
    - 할인 정책 적용
    - 수수료 정책 적용

- ~~배포된 서버 주소 : http://airbnb-alb-ap-northeast-2-668068179.ap-northeast-2.elb.amazonaws.com/~~
- ~~API 문서 주소 : http://airbnb-alb-ap-northeast-2-668068179.ap-northeast-2.elb.amazonaws.com/docs/index.html~~
    
## 1. 인프라 구조

![](https://user-images.githubusercontent.com/45728407/171101612-3f5a3fa9-3b9b-4496-943f-4409946682f3.png)
그림-1

전체 인프라 구조에 대해 간략하게 설명하고 자세한 내용은 아래에서 설명하겠습니다.

- CI/CD 구축 : Github Action, CodeDeploy, S3, Docker 이용
- 망분리 환경 구축 : VPC 에서 2개의 가용영역에 각각 public subnet 과 private subnet 구축
  - public subnet : 인터넷과 직접적으로 연결되어있는 환경
  - private subnet : 인터넷과 간접적으로 연결 혹은 연결되어있지 않은 환경
    - WAS, DB 가 private subnet 환경에 구축되어있음
- 로드밸런싱 : private subnet 으로 요청 전달 및 트래픽 분산을 위해 설정
  - 원래는 2개 이상의 인스턴스와 연결되어 부하분산을 해야하지만, 비용 문제로 실습만 해보고 운용하진 않음
  
### 1-1. CI/CD 구축

CI/CD 는 많은 작업이 Github Action 에서 진행되며, 다음과 같은 작업들이 있습니다

| **Workflows** | **대상 브랜치** | **구축 여부** | 
|:-------------:|:----------:|:---------:|
|   백엔드 개발 CI   |     be     |     O     |
| 백엔드 운영 CI/CD  | deploy-be  |     O     |
|   프론트 개발 CI   |     FE     |     X     |
| 프론트 운영 CI/CD  | deploy-fe  |    예정     |

현재 백엔드는 개발 및 운영 CI/CD 구성을 마친 상태이며, 곧 프론트 운영 CI/CD 도 구축될 예정입니다.

현재 구축된 Workflows 는 다음 순서로 작업이 진행됩니다.

#### 1-1-1. 백엔드 개발 CI

해당 workflow 는 백엔드 메인 개발소스가 있는 `be` 브랜치 대상으로 실행됩니다.
테스트 코드 및 빌드 성공 여부만 판단합니다.

1. Source 체크아웃
2. JDK 11 세팅 - 빌드 환경 구성
3. 프로젝트 빌드 - `gradle build`

#### 1-1-2. 백엔드 운영 CI/CD

해당 workflow 는 백엔드 배포 브랜치인 `deploy-be` 브랜치 대상으로 실행됩니다.
테스트 코드 및 빌드 성공 여부만 판단합니다.

1. Source 체크아웃
2. JDK 11 세팅 - 빌드 환경 구성
3. 프로젝트 빌드 - `gradle build`
4. API 문서 정상 빌드여부 확인
5. (API 문서가 정상이 아닐 경우) 프로젝트 재빌드 `gradle bootJar`
6. API 문서 정상 빌드여부 재확인
   - API 문서 정상적으로 빌드 안되면 실패
7. 프로젝트 실행에 필요한 Docker 환경변수 파일 생성
8. `.zip` 으로 패키징
9. AWS 인증 (access-key-id, secret-access-key)
10. AWS S3 에 업로드
11. AWS CodeDeploy 의 배포작업 실행
    - 이 작업이 실행되며 WAS 내부에서 SpringBoot 컨테이너 재실행(무중단은 아님)

여기서 API 문서 빌드 여부를 확인하는 이유는, 문서 자동화 도구로 사용한 `Spring REST Docs` 빌드에 문제가 해결되지 않아서 입니다.
현재 API 문서가 첫 번째 빌드에는 Jar 에 포함되지 않는 문제가 있습니다.
하지만 해결책을 찾는데 시간이 너무 오래 걸려서, 일단 임시방편으로 **두번 빌드하는 방법**을 선택하여 진행중입니다.
(추후 시간에 여유가 생기면 문제를 해결해볼 예정입니다.)

그러나 두 번의 `gradle build` 는 많은 리소스를 소모할 것이라 생각했습니다.
그래서 두 번째 빌드에서는 `gradle bootJar` 로 jar 파일만 다시 패키징 하여, 빌드에 소요되는 시간을 줄이고자 하였습니다.

![image](https://user-images.githubusercontent.com/45728407/171106677-8931565d-1192-4940-99bb-79ebb5abccee.png)
그림-2-1 (첫 번째 빌드 `gradle build` : 약 54초 소요)

![image](https://user-images.githubusercontent.com/45728407/171106795-8ecc553a-c566-4585-b1cb-fb12cd5247a9.png)
그림-2-2 (두 번째 빌드 `gradle bootJar` : 약 10초 소요)

### 1-2. 망분리 환경 구축

![image](https://user-images.githubusercontent.com/45728407/171114685-20f80be8-126e-4ff3-bd56-529b45b1e11c.png)
<br>그림-3 (점선 부분은 실습만 해보고, 인스턴스를 삭제한 항목들)

VPC 에서 2개의 가용영역에 각각 public, private subnet 을 구축했습니다.
그리고 요구사항에 따라, WAS 인스턴스와 RDS 인스턴스를 다른 subnet 으로 구성했습니다.
WAS 인스턴스와 RDS 모두 이중화 되는것이 좋지만, 비용상의 문제로 실습만 해보고 운용은 하지 않습니다.

`WAS` 의 인바운드 규칙은 출발지에 상관없이 HTTP, HTTPS 에 열려있습니다.
그리고 관리를 위해 `NAT & Bastion` 에 한해 ssh 접속이 가능하도록 설정되어 있습니다.

`RDS` 의 인바운드 규칙은 `WAS` 에서 출발하는 3306 포트 트래픽에 대해서만 열려있습니다.

### 1-3. 로드밸런싱

`그림-3` 에서 ALB 는 public subnet 에 속해있고, private subnet 의 `WAS` 인스턴스들에게 요청을 포워딩 합니다.

### 2. 도메인 모델 설계

![image](https://user-images.githubusercontent.com/45728407/171122283-38cd7e1b-2f7b-4111-89a1-3b7c6902090c.png)
<br>그림-4

주요 도메인들을 추려내어, 설계한 구조입니다.
파란색으로 표시한 도메인들은, 영속화할(DB에 저장할) 도메인들 입니다.

`스케줄(Schedule)` 은 DB에 저장되지 않는 도메인이고, 몇일부터 몇일까지 몇명이 어떤 숙소에 묵을건지를 저장합니다.
그리고 `숙소(Accommodation)` 으로 부터 할인된 가격까지 계산합니다.

그리고 이 중에 `할인 정책(DiscountPolicy)`, `할인 조건(DiscountCondition)`, `수수료 정책(FeesPolicy)` 는 DB에 저장해야하는지,
만약 저장한다면 어떤 방식으로 저장하는게 좋은지는 아직 고민중입니다.

<img width="225" alt="image" src="https://user-images.githubusercontent.com/45728407/171124642-183b8488-3ef0-42c9-bf0e-389ea412d52a.png">
<br>그림-5
<br>
이번 프로젝트에서는 `그림-5` 과 같은 할인 정책과, 수수료들이 있습니다.

<img width="978" alt="image" src="https://user-images.githubusercontent.com/45728407/171124894-455e638f-77bd-4181-985a-0124a1d3d14b.png">
<br>그림-6

처음에는 `그림-6` 처럼, 각 숙소마다 할인 정책을 붙이는 방향으로 생각했는데요.
조금 더 요구사항을 살펴보니, 숙소와 관계없이 결제 시 일정에 따른 할인이라는 것을 알 수 있었습니다.

![](https://user-images.githubusercontent.com/45728407/171122283-38cd7e1b-2f7b-4111-89a1-3b7c6902090c.png)
<br>그림-7

그래서 최종적으로 `그림-7` 같은 형태로 생각하게 되었습니다.
