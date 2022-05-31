import { createContext } from 'react';
import { checkContextInterface } from 'contexts/checkcontext/checkContext.types';

export const CheckContext = createContext<checkContextInterface | null>(null);
