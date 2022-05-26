import React from 'react';

import { GNB_TEXT } from 'constant/constant';
import MenuIcon from '@mui/icons-material/Menu';
import PersonOutlineOutlinedIcon from '@mui/icons-material/PersonOutlineOutlined';
import { IconButton } from '@mui/material';

import {
  Nav,
  Logo,
  GNBList,
  GNBItem,
  Menu,
  MenuItem,
} from 'components/GNB/GNB.styled';

function GNB(): JSX.Element {
  const gnbItems = GNB_TEXT.map((el: { id: number; text: string }) => (
    <GNBItem key={el.id}>{el.text}</GNBItem>
  ));

  return (
    <Nav>
      <Logo>AIRBNB</Logo>
      <GNBList>{gnbItems}</GNBList>
      <Menu>
        <MenuItem>
          <IconButton aria-label="menu">
            <MenuIcon />
          </IconButton>
          <IconButton aria-label="user" size="large">
            <PersonOutlineOutlinedIcon />
          </IconButton>
        </MenuItem>
      </Menu>
    </Nav>
  );
}

export default GNB;
