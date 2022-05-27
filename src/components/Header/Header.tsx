import React from 'react';

import { Nav, Logo } from 'components/Header/Header.styled';

import GNB from 'components/GNB/GNB';
import Menu from 'components/Menu/Menu';

function Header(): JSX.Element {
  return (
    <Nav>
      <Logo>AIRBNB</Logo>
      <GNB />
      <Menu />
    </Nav>
  );
}

export default Header;
