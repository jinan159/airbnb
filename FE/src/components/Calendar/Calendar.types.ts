export interface DateInfosInterface {
  id: number;
  year?: number;
  month?: number;
  date?: number;
}

export interface DateInfoInterface {
  year: number;
  month: number;
}

export interface CalendarPropsInterface {
  dateInfo: DateInfoInterface;
  calendarClickCount: { current: number };
}
