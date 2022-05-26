import React from 'react';
import styled from 'styled-components';
import { Routes, Route } from 'react-router-dom';
import Hero from 'components/Hero/Hero';
import Home from 'pages/Home/Home';
import Rooms from 'pages/Rooms/Rooms';

const Container = styled.div`
  font-size: ${({ theme: { fontSizes } }) => fontSizes.md};
`;

function App(): JSX.Element {
  return (
    <Container>
      <Routes>
        <Route element={<Hero />}>
          <Route index element={<Home />} />
          <Route path="/rooms" element={<Rooms />} />
        </Route>
      </Routes>
    </Container>
  );
}

export default App;
