import React from 'react';
import { UserMenu, MenuBtn } from 'components/Menu/Menu.styled';
import { USER_MENU_IMGS } from 'constant/constant';

function Menu(): JSX.Element {
  const userMenu = USER_MENU_IMGS.map(el => (
    <MenuBtn key={el.id} num={el.id} type="button">
      <img src={el.src} alt={el.alt} />
    </MenuBtn>
  ));

  return <UserMenu>{userMenu}</UserMenu>;
}

export default Menu;
