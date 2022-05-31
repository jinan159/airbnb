import { BASIC_MONTH_INFOS } from 'constant';
import {
  CalendarInterface,
  CalendarActionInterface,
} from 'components//CalendarModal/CalendarModal.types';

const cur: Date = new Date(Date.now());

export const initialCalendarState: CalendarInterface[] = [
  {
    id: 1,
    year: cur.getFullYear(),
    month: cur.getMonth() + BASIC_MONTH_INFOS.thisMonth,
  },
  {
    id: 2,
    year: cur.getFullYear(),
    month: cur.getMonth() + BASIC_MONTH_INFOS.nextMonth,
  },
];

function updateCalendarState(state: CalendarInterface[]): CalendarInterface[] {
  const copy = JSON.parse(JSON.stringify(state));
  const recentDate = copy[copy.length - 1];
  const DECEMBER = 12;

  if (recentDate.month === DECEMBER) {
    const newObj = {
      id: recentDate.id + 1,
      year: recentDate.year + 1,
      month: 1,
    };
    return [...copy, newObj];
  }

  const newObj = {
    id: recentDate.id + 1,
    year: recentDate.year,
    month: recentDate.month + 1,
  };

  return [...copy, newObj];
}

export function calendarReducer(
  state: CalendarInterface[],
  action: CalendarActionInterface,
): CalendarInterface[] {
  switch (action.type) {
    case 'ADD_CALENDAR':
      return updateCalendarState(state);
    default:
      return state;
  }
}
