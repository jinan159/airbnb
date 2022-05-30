export const GNB_TEXTS = [
  { id: 1, text: '숙소' },
  { id: 2, text: '체험' },
  { id: 3, text: '온라인 체험' },
];

export const USER_MENU_IMGS = [
  { id: 1, src: '/assets/images/menu.svg', alt: '메뉴' },
  { id: 2, src: '/assets/images/user.svg', alt: '유저정보' },
];

export const CHECK_INFOS = [
  { id: 1, title: '체크인', label: 'checkin', placeHolder: '날짜 입력' },
  { id: 2, title: '체크아웃', label: 'checkout', placeHolder: '날짜 입력' },
];

export const FARE_INFOS = {
  title: '요금',
  label: 'fare',
  placeHolder: '금액대 설정',
};

export const PERSONNEL_INFOS = {
  title: '인원',
  label: 'personnel',
  placeHolder: '게스트 추가',
};

export const BASIC_MONTH_INFOS = {
  thisMonth: 1,
  nextMonth: 2,
};

const CAROUSELUNIT_LEFT = 40.4;
const CAROUSELUNIT_RIGHT = -40.4;

export const CALENDAR_BUTTON_INFOS = [
  {
    id: 1,
    src: './assets/images/left.svg',
    alt: '캘린더 왼쪽 버튼',
    className: 'left',
    carouselUnit: CAROUSELUNIT_LEFT,
  },
  {
    id: 2,
    src: './assets/images/right.svg',
    alt: '캘린더 오른쪽 버튼',
    className: 'right',
    carouselUnit: CAROUSELUNIT_RIGHT,
  },
];

export const DAY_TEXTS = ['일', '월', '화', '수', '목', '금', '토'];
