import React from 'react';
import { GNB_TEXT } from 'constant/constant';
import { Nav, GNBList, GNBItem, Menu, MenuItem } from './GNB.styeld';

function GNB() {
  return (
    <Nav>
      <GNBList>
        <GNBItem />
      </GNBList>
      <Menu>
        <MenuItem />
      </Menu>
    </Nav>
  );
}

export default GNB;
