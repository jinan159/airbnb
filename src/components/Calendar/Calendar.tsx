import React, { useMemo } from 'react';

import { DAY_TEXTS } from 'constant/constant';

import {
  CarouselItem,
  CalendarTitle,
  CalendarContent,
  Week,
  WeekCell,
  Dates,
  DateCell,
} from './Calendar.styled';

function createTotalDateArr(
  weekCount: number,
  curMonthOneDateDay: number,
): { id: number; value: number }[] {
  const dates = Array.from(new Array(weekCount), (_, i) => {
    if (i <= curMonthOneDateDay) return { id: i, value: 0 };

    return { id: i, value: i - curMonthOneDateDay };
  });

  return dates;
}

function Calendar(): JSX.Element {
  const [curYear, curMonth, curDate] = useMemo(() => {
    const cur = new Date(Date.now());
    return [cur.getFullYear(), cur.getMonth() + 1, cur.getDate()];
  }, []);
  // 해당 달의 1일에 요일 구하기
  // const curMonthOneDateDay = new Date(2021, 4, 1).getDay();
  console.log(curMonth);
  const curMonthOneDateDay = new Date(curYear, curMonth - 1, 1).getDay();
  // 해당 달의 일수 구하기
  // const curMonthTotalDate = new Date(2021, 5, 0).getDate();
  const curMonthTotalDate = new Date(curYear, curMonth, 0).getDate();
  const totalDateCellCount = curMonthOneDateDay + curMonthTotalDate;

  const dates = createTotalDateArr(totalDateCellCount, curMonthOneDateDay - 1);

  const datesCells = dates.map(el => {
    if (el.value === 0) return <DateCell key={el.id} />;
    return <DateCell key={el.id}>{el.value}</DateCell>;
  });

  const week = DAY_TEXTS.map(el => <WeekCell key={el}>{el}</WeekCell>);

  return (
    <CarouselItem>
      <CalendarTitle>
        {curYear}년 {curMonth}월
      </CalendarTitle>
      <CalendarContent>
        <Week>{week}</Week>
        <Dates>{datesCells}</Dates>
      </CalendarContent>
    </CarouselItem>
  );
}

export default Calendar;
