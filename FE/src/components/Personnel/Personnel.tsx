import React, { useContext, useEffect, useState } from 'react';

import { InputText } from 'components/InputText';
import { PersonnelModal } from 'components/PersonnelModal';

import { PersonnelContext } from 'contexts/personnelcontext/personnelContext';

import {
  PersonnelContainer,
  PersonnelInputClearBtn,
} from 'components/Personnel/Personnel.styled';

import { PERSONNEL_INFOS } from 'constant';

export function Personnel(): JSX.Element {
  const [personnelText, setPersonnelText] = useState<string>('');
  const [isShow, setIsShow] = useState<boolean>(false);
  const [isClearBtnShow, setIsClearBtnShow] = useState<boolean>(false);
  const personnelContext = useContext(PersonnelContext);
  const { adult, child, infant } = personnelContext.personnelState;

  useEffect(() => {
    activeClearBtn();

    if (adult === 0 && (child > 0 || infant > 0)) setPersonnelText('');
    else if (adult === 0 && infant === 0) setPersonnelText('');
    else if (adult > 0 && infant === 0)
      setPersonnelText(`게스트 ${adult + child}명`);
    else if (adult === 0 && infant > 0) setPersonnelText(`유아 ${infant}명`);
    else if (adult > 0 && infant > 0)
      setPersonnelText(`게스트 ${adult + child}명 유아 ${infant}명`);
  }, [adult, child, infant]);

  const activeClearBtn = () => {
    if (adult > 0 || child > 0 || infant > 0) setIsClearBtnShow(true);
    else if (adult === 0 && child === 0 && infant === 0)
      setIsClearBtnShow(false);
  };

  const handleClickPersonnelModalShow = () => setIsShow(prev => !prev);

  const handleClickPersonnelClearBtn = () => {
    setPersonnelText('');
    setIsShow(prev => !prev);
    setIsClearBtnShow(prev => !prev);
    personnelContext.personnelDispatch({ type: 'CLEAR' });
  };

  return (
    <>
      <PersonnelContainer onClick={handleClickPersonnelModalShow}>
        <InputText info={PERSONNEL_INFOS} value={personnelText} />
        <PersonnelInputClearBtn
          isClearBtnShow={isClearBtnShow}
          type="button"
          onClick={handleClickPersonnelClearBtn}
        >
          <img src="./assets/images/x-circle.svg" alt="인원 초기화 버튼" />
        </PersonnelInputClearBtn>
      </PersonnelContainer>
      <PersonnelModal
        isShow={isShow}
        handleClickPersonnelModalShow={handleClickPersonnelModalShow}
      />
    </>
  );
}
