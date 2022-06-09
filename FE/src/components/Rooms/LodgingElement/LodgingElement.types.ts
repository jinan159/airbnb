export interface ILodgingElementProps {
  lodgingInfo: {
    id: number;
    imageUrl: string;
    exp: string;
    title: string;
    infoFirst: string[];
    infoSecond: string[];
    star: number;
    review: number;
    price: number;
    totalPrice: number;
  };
  isFetching: boolean;
}
