import React from 'react';
import { Outlet } from 'react-router-dom';

import { Container } from 'components/Hero/Hero.styled';

import Header from 'components/Header/Header';
import Search from 'components/Search/Search';

function Hero(): JSX.Element {
  return (
    <Container src="/assets/images/hero-img.png">
      <Header />
      <Search />
      <Outlet />
    </Container>
  );
}

export default Hero;
