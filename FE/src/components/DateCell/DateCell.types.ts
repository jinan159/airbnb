export interface DateCellProps {
  date?: number;
  past?: boolean;
  flag?: boolean;
}

export interface DateCellInterface {
  date?: number;
  past?: boolean;
  calendarClickCount: { current: number };
}
