import { createContext } from 'react';
import { checkFlagContextInterface } from './checkFlagContext.types';

export const CheckFlagContext = createContext<checkFlagContextInterface | null>(
  null,
);
