import React from 'react';
import styled from 'styled-components';
import { Routes, Route } from 'react-router-dom';
import Hero from 'components/Hero/Hero';
import Home from 'pages/Home/Home';
import Rooms from 'pages/Rooms/Rooms';

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
