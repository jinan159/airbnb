import React from 'react';

import {
  SearchBarTitle,
  SearchBarInputText,
} from 'components/InputText/InputText.styled';

import { InputTextProps } from 'components/InputText/InputText.types';

export function InputText({ info, check }: InputTextProps): JSX.Element {
  return (
    <section>
      <label htmlFor={info.label}>
        <SearchBarTitle>{info.title}</SearchBarTitle>
      </label>
      <SearchBarInputText
        type="text"
        id={info.label}
        placeholder={info.placeHolder}
        readOnly
        value={check}
      />
    </section>
  );
}
