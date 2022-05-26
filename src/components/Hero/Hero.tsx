import React from 'react';
import { Header, Logo } from 'components/Hero/Hero.styled';
import { Outlet } from 'react-router-dom';

function Hero() {
  return (
    <Header>
      <Logo>AIRBNB</Logo>
      <Outlet />
    </Header>
  );
}

export default Hero;
