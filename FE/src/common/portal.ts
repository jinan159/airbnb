import ReactDom from 'react-dom';

import { ModalPortalProps } from 'common/portal.types';

function ModalPortal({ children }: ModalPortalProps): JSX.Element {
  const el = document.getElementById('modal') as HTMLElement;
  return ReactDom.createPortal(children, el);
}

export default ModalPortal;
