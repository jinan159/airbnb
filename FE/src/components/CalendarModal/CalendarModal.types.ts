export interface CalendarProps {
  show: boolean;
  handleClickShow: () => void;
  calendarClickCount: { current: number };
}

export interface CalendarInterface {
  id: number;
  year: number;
  month: number;
}

export interface CalendarActionInterface {
  type: string;
}

export interface CarouselItemContainerProps {
  pos: number;
}
