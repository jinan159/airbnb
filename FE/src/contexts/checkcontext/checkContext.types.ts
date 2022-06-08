import { Dispatch, SetStateAction } from 'react';

export interface checkContextInterface {
  checkIn: string;
  checkOut: string;
  mouseOverCheckOut: string;
  setCheckIn: Dispatch<SetStateAction<string>>;
  setCheckOut: Dispatch<SetStateAction<string>>;
  setMouseOverCheckOut: Dispatch<SetStateAction<string>>;
}
