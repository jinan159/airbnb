import React from 'react';
import { Container } from 'components/Hero/Hero.styled';
import { Outlet } from 'react-router-dom';
import Header from 'components/Header/Header';

function Hero(): JSX.Element {
  return (
    <Container src="/assets/images/hero-img.png">
      <Header />
      <Outlet />
    </Container>
  );
}

export default Hero;
