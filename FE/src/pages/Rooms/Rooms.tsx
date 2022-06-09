import React, { useEffect } from 'react';
import { useOutletContext } from 'react-router-dom';

import { Lodging } from 'components/Rooms/Lodging';

import { Maps } from 'components/Rooms/Maps';

import { IRoomsOutletContext } from './Rooms.types';

import { RoomsContainer } from './Rooms.styled';

export function Rooms(): JSX.Element {
  const { setIsSearchShowing } = useOutletContext<IRoomsOutletContext>();

  useEffect(() => {
    setIsSearchShowing(true);

    return () => {
      setIsSearchShowing(false);
    };
  }, []);

  return (
    <RoomsContainer>
      <Lodging />
      <Maps />
    </RoomsContainer>
  );
}
