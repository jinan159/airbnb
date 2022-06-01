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

  useEffect(() => {
    activeClearBtn();
  }, [
    personnelContext.personnelState.adult,
    personnelContext.personnelState.child,
    personnelContext.personnelState.infant,
  ]);

  const activeClearBtn = () => {
    if (
      personnelContext.personnelState.adult > 0 ||
      personnelContext.personnelState.child > 0 ||
      personnelContext.personnelState.infant > 0
    )
      setIsClearBtnShow(true);
    else if (
      personnelContext.personnelState.adult === 0 &&
      personnelContext.personnelState.child === 0 &&
      personnelContext.personnelState.infant === 0
    )
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
