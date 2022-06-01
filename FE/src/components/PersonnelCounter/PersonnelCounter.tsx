import React, { useContext } from 'react';

import { PersonnelContext } from 'contexts/personnelcontext/personnelContext';

import { IPersonnelState } from 'components/Personnel/Presonnel.types';
import {
  Container,
  Button,
  ButtonImg,
  Number,
} from './PersonnelCounter.styled';

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

  const handleClickPersonnelMinus = () => {
    personnelContext.personnelDispatch({ type: `SUBTRACT_${actionTypeName}` });
  };

  const handleClickPersonnelPlus = () => {
    personnelContext.personnelDispatch({ type: `ADD_${actionTypeName}` });
  };

  const number = distinguishPersonnelValue(
    actionTypeName,
    personnelContext.personnelState,
  );

  return (
    <Container>
      <Button onClick={handleClickPersonnelMinus}>
        <ButtonImg src="./assets/images/minuse.svg" alt="인원 마이너스" />
      </Button>
      <Number>{number}</Number>
      <Button onClick={handleClickPersonnelPlus}>
        <ButtonImg src="./assets/images/pluse.svg" alt="인원 플러스" />
      </Button>
    </Container>
  );
}
