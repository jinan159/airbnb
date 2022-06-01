import { IPersonnelState } from 'components/Personnel/Presonnel.types';
import { Dispatch } from 'react';

export interface IPersonnelContext {
  personnelState: IPersonnelState;
  personnelDispatch: Dispatch<{ type: string }>;
}
