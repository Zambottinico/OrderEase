import { DiningTableState } from './DiningTableState.enum';

export class Mesa {
  id: number;
  name: string;
  state: DiningTableState;
  people: number;
  idClient: number;
}
