import React from 'react';
import styled from 'styled-components';
import { Button } from '@mui/material';
import { Routes, Route } from 'react-router-dom';
import Hero from 'components/Hero/Hero';
import Home from 'pages/Home/Home';
import Rooms from 'pages/Rooms/Rooms';

const CustomDiv = styled.div`
  width: 50px;
  height: 50px;
  background-color: black;
`;

const CustomButton = styled(Button)`
  width: 100px;
  height: 100px;
`;

function App() {
  return (
    <Routes>
      <Route element={<Hero />}>
        <Route index element={<Home />} />
        <Route path="/rooms" element={<Rooms />} />
      </Route>
    </Routes>
  );
}

export default App;
