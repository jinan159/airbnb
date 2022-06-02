import React, { useEffect } from 'react';
import { useOutletContext } from 'react-router-dom';

import { IRoomsOutletContext } from './Rooms.types';

export function Rooms(): JSX.Element {
  const { setIsSearchShowing } = useOutletContext<IRoomsOutletContext>();

  useEffect(() => {
    setIsSearchShowing(true);
    return () => {
      setIsSearchShowing(false);
    };
  }, []);

  return <div />;
}
