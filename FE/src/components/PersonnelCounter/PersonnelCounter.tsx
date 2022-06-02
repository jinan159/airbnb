import React, { useContext } from 'react';

import { PersonnelContext } from 'contexts/personnelcontext/personnelContext';

import { IPersonnelState } from 'components/Personnel/Presonnel.types';

import { PERSONNEL_MAX_VALUE, PERSONNEL_MIN_VALUE } from 'constant';

import { Container, Button, ButtonImg, Count } from './PersonnelCounter.styled';

const distinguishPersonnelValue = (
  actionTypeName: string,
  PersonnelValue: IPersonnelState,
) => {
  switch (actionTypeName) {
    case 'ADULT':
      return PersonnelValue.adult;
    case 'CHILD':
      return PersonnelValue.child;
    default:
      return PersonnelValue.infant;
  }
};

export function PersonnelCounter({
  actionTypeName,
}: {
  actionTypeName: string;
}): JSX.Element {
  const personnelContext = useContext(PersonnelContext);

  const count = distinguishPersonnelValue(
    actionTypeName,
    personnelContext.personnelState,
  );

  const countGreaterThanMin = count > PERSONNEL_MIN_VALUE;
  const countLessThanMax = count < PERSONNEL_MAX_VALUE;

  const minusBtnImg = countGreaterThanMin
    ? './assets/images/minuse.svg'
    : './assets/images/minuse-off.svg';
  const plusBtnImg = countLessThanMax
    ? './assets/images/pluse.svg'
    : './assets/images/pluse-off.svg';

  const { adult } = personnelContext.personnelState;

  const handleClickPersonnelMinus = () => {
    subtractPersonnelCount();
  };

  const subtractPersonnelCount = () => {
    if (countGreaterThanMin)
      personnelContext.personnelDispatch({
        type: `SUBTRACT_${actionTypeName}`,
      });
  };

  const handleClickPersonnelPlus = () => {
    addPersonnelCount();
    addPersonnelAdultCountWhenRestCountZero();
  };

  const addPersonnelCount = () => {
    if (countLessThanMax)
      personnelContext.personnelDispatch({ type: `ADD_${actionTypeName}` });
  };

  const addPersonnelAdultCountWhenRestCountZero = () => {
    if (
      adult === PERSONNEL_MIN_VALUE &&
      (actionTypeName === 'INFANT' || actionTypeName === 'CHILD')
    )
      personnelContext.personnelDispatch({ type: `ADD_ADULT` });
  };

  return (
    <Container>
      <Button onClick={handleClickPersonnelMinus}>
        <ButtonImg src={minusBtnImg} alt="인원 마이너스" />
      </Button>
      <Count>{count}</Count>
      <Button onClick={handleClickPersonnelPlus}>
        <ButtonImg src={plusBtnImg} alt="인원 플러스" />
      </Button>
    </Container>
  );
}
