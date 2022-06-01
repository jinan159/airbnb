import React, { useState } from 'react';

import { InputText } from 'components/InputText';
import { PersonnelModal } from 'components/PersonnelModal';

import { PersonnelContainer } from 'components/Personnel/Personnel.styled';

import { PERSONNEL_INFOS } from 'constant';

export function Personnel(): JSX.Element {
  const [isShow, setIsShow] = useState<boolean>(false);

  const handleClickPersonnelModalShow = () => {
    setIsShow(prev => !prev);
  };

  return (
    <>
      <PersonnelContainer onClick={handleClickPersonnelModalShow}>
        <InputText info={PERSONNEL_INFOS} />
        {/* <PersonnelInputClearBtn></PersonnelInputClearBtn> */}
      </PersonnelContainer>
      <PersonnelModal
        isShow={isShow}
        handleClickPersonnelModalShow={handleClickPersonnelModalShow}
      />
    </>
  );
}
