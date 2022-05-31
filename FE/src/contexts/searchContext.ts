import { createContext } from 'react';
import { searchContextInterface } from 'contexts/searchContext.types';

export const SearchCtx = createContext<searchContextInterface | null>(null);
