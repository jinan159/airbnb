import React from 'react';

import {
  Container,
  Button,
  ButtonImg,
  Number,
} from './PersonnelCounter.styled';

export function PersonnelCounter(): JSX.Element {
  return (
    <Container>
      <Button>
        <ButtonImg src="./assets/images/minuse.svg" alt="인원 마이너스" />
      </Button>
      <Number>2</Number>
      <Button>
        <ButtonImg src="./assets/images/pluse.svg" alt="인원 플러스" />
      </Button>
    </Container>
  );
}
