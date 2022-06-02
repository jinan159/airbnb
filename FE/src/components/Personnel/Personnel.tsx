import React, { useContext, useEffect, useState } from 'react';

import { InputText } from 'components/InputText';
import { PersonnelModal } from 'components/PersonnelModal';

import { PersonnelContext } from 'contexts/personnelcontext/personnelContext';

import {
  PersonnelContainer,
  PersonnelInputClearBtn,
} from 'components/Personnel/Personnel.styled';

import { PERSONNEL_TEXT } from 'constant';

export function Personnel(): JSX.Element {
  const [personnelText, setPersonnelText] = useState<string>('');
  const [isShow, setIsShow] = useState<boolean>(false);
  const [isClearBtnShow, setIsClearBtnShow] = useState<boolean>(false);
  const personnelContext = useContext(PersonnelContext);
  const { adult, child, infant } = personnelContext.personnelState;
  const {
    adultEqualtoZero,
    adultGreatethanZero,
    childEqualtoZero,
    childGreatethanZero,
    infantEqualtoZero,
    infantGreatethanZero,
  } = {
    adultEqualtoZero: adult === 0,
    adultGreatethanZero: adult > 0,
    childEqualtoZero: child === 0,
    childGreatethanZero: child > 0,
    infantEqualtoZero: infant === 0,
    infantGreatethanZero: infant > 0,
  };

  useEffect(() => {
    showClearBtn();
    updateInputPersonnelText();
  }, [adult, child, infant]);

  const showClearBtn = () => {
    if (adultGreatethanZero || childGreatethanZero || infantGreatethanZero)
      setIsClearBtnShow(true);
    else if (adultEqualtoZero && childEqualtoZero && infantEqualtoZero)
      setIsClearBtnShow(false);
  };

  const handleClickPersonnelModalShow = () => setIsShow(prev => !prev);

  const handleClickPersonnelClearBtn = () =>
    clearInputPersonnelTextAndClearBtn();

  const updateInputPersonnelText = () => {
    if (adultEqualtoZero && (childGreatethanZero || infantGreatethanZero)) {
      setPersonnelText('');
    } else if (adultGreatethanZero) {
      if (infantEqualtoZero) setPersonnelText(`게스트 ${adult + child}명`);
      else if (infantGreatethanZero)
        setPersonnelText(`게스트 ${adult + child}명 유아 ${infant}명`);
    }
  };

  const clearInputPersonnelTextAndClearBtn = () => {
    setPersonnelText('');
    setIsShow(prev => !prev);
    setIsClearBtnShow(prev => !prev);
    personnelContext.personnelDispatch({ type: 'CLEAR' });
  };

  return (
    <>
      <PersonnelContainer onClick={handleClickPersonnelModalShow}>
        <InputText info={PERSONNEL_TEXT} value={personnelText} />
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
