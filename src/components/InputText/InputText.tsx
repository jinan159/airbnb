import React from 'react';
import {
  SearchBarTitle,
  SearchBarInputText,
} from 'components/InputText/InputText.styled';

interface inputInfoProps {
  info: {
    title: string;
    label: string;
    placeHolder: string;
  };
}

function InputText({ info }: inputInfoProps): JSX.Element {
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
