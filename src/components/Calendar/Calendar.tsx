import React from 'react';

import { DAY_TEXTS } from 'constant/constant';

import DateCell from 'components/DateCell/DateCell';

import {
  DateInfosInterface,
  DateInfoInterface,
} from 'components/Calendar/Calendar.types';
import {
  CarouselItem,
  CalendarTitle,
  CalendarContent,
  Week,
  WeekCell,
  Dates,
} from './Calendar.styled';

function createTotalDateArr(
  weekCount: number,
  curMonthOneDateDay: number,
  dateInfo: DateInfoInterface,
): DateInfosInterface[] {
  const dates = Array.from(new Array(weekCount), (_, i) => {
    if (i <= curMonthOneDateDay) return { id: i, date: 0 };

    return {
      id: i,
      year: dateInfo.year,
      month: dateInfo.month,
      date: i - curMonthOneDateDay,
    };
  });

  return dates;
}

function Calendar({ dateInfo }: { dateInfo: DateInfoInterface }): JSX.Element {
  // 해당 달의 1일에 요일 구하기
  const curMonthOneDateDay: number = new Date(
    dateInfo.year,
    dateInfo.month - 1,
    1,
  ).getDay();
  // 해당 달의 일수 구하기
  const curMonthTotalDate: number = new Date(
    dateInfo.year,
    dateInfo.month,
    0,
  ).getDate();
  const totalDateCellCount: number = curMonthOneDateDay + curMonthTotalDate;
  const dates: DateInfosInterface[] = createTotalDateArr(
    totalDateCellCount,
    curMonthOneDateDay - 1,
    dateInfo,
  );

  const week = DAY_TEXTS.map(el => <WeekCell key={el}>{el}</WeekCell>);

  const distinguishPast = (el: DateInfosInterface): boolean => {
    const date = new Date(`${el.year}-${el.month}-${el.date}`);
    const now = new Date(Date.now());
    const cur = new Date(
      `${now.getFullYear()}-${now.getMonth() + 1}-${now.getDate()}`,
    );

    return cur <= date;
  };

  const datesCells = dates.map(el => {
    if (el.date === 0) return <DateCell key={el.id} />;

    const past = distinguishPast(el);

    return <DateCell key={el.id} date={el.date} past={past} />;
  });

  return (
    <CarouselItem>
      <CalendarTitle>
        {dateInfo.year}년 {dateInfo.month}월
      </CalendarTitle>
      <CalendarContent>
        <Week>{week}</Week>
        <Dates>{datesCells}</Dates>
      </CalendarContent>
    </CarouselItem>
  );
}

export default Calendar;
