import React from 'react';
import { Header } from 'components/Hero/Hero.styled';
import { Outlet } from 'react-router-dom';
import GNB from 'components/GNB/GNB';

function Hero(): JSX.Element {
  return (
    <Header>
      <GNB />
      <Outlet />
    </Header>
  );
}

export default Hero;
