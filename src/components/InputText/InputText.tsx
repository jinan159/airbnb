import React from 'react';

import {
  SearchBarTitle,
  SearchBarInputText,
} from 'components/InputText/InputText.styled';

import { InputTextProps } from 'components/InputText/InputText.types';

function InputText({ info }: InputTextProps): JSX.Element {
  return (
    <section>
      <label htmlFor={info.label}>
        <SearchBarTitle>{info.title}</SearchBarTitle>
      </label>
      <SearchBarInputText
        type="text"
        id={info.label}
        placeholder={info.placeHolder}
      />
    </section>
  );
}

export default InputText;
