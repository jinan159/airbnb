import { DateInfosInterface } from 'components/Calendar/Calendar.types';

export interface DateCell {
  dateInfo?: DateInfosInterface;
  past?: boolean;
}

export interface DateCellContentProps extends DateCell {
  selectFlag?: boolean;
  rangeFlag?: boolean;
}

export interface DateCellInterface extends DateCell {
  calendarClickCount: { current: number };
}

export interface DateCellCircleProps extends DateCell {
  rangeFlag?: boolean;
}
