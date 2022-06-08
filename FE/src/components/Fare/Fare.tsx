import React, { useContext, useState } from 'react';
import { FARE_INFO } from 'constant';
import { InputText } from 'components/InputText';
import { FareContainer } from './Fare.styled';
import { FareModal } from './FareModal';
import { FareContext } from '../../contexts/farecontext/farecontext';

export function Fare(): JSX.Element {
  const { Slidevalue } = useContext(FareContext);
  const [show, setShow] = useState<boolean>(false);
  const modalShowHandler = (boolean: boolean): void => {
    setShow(boolean);
  };
  const info = { ...FARE_INFO, placeHolder: Slidevalue.placeHolder };
  return (
    <>
      <FareContainer onClick={() => modalShowHandler(true)}>
        <InputText info={info} value={Slidevalue.placeHolder} />
      </FareContainer>
      <FareModal show={show} modalShowHandler={modalShowHandler} />
    </>
  );
}
