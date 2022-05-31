import { Dispatch, SetStateAction } from 'react';

export interface checkFlagContextInterface {
  selectFlag: boolean;
  rangeFlag: boolean;
  setSelectFlag: Dispatch<SetStateAction<boolean>>;
  setRangeFlag: Dispatch<SetStateAction<boolean>>;
}
