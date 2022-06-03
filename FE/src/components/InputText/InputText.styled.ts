import styled from 'styled-components';

export const SearchBarTitle = styled.h2`
  display: block;
  font-weight: ${({ theme: { fontWeights } }) => fontWeights.bold};
  font-size: ${({ theme: { fontSizes } }) => fontSizes.sm};
  line-height: ${({ theme: { lineHeights } }) => lineHeights.shorter};
  cursor: pointer;
`;

export const SearchBarInputText = styled.input`
  margin-top: 0.4rem;
  font-size: ${({ theme: { fontSizes } }) => fontSizes.md};
  line-height: ${({ theme: { lineHeights } }) => lineHeights.base};
  color: ${({ theme: { colors } }) => colors.grey2};
  border: none;
  background-color: transparent;
  cursor: pointer;

  &:focus {
    outline: none;
  }
`;
