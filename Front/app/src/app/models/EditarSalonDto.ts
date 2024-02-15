import { Mesa } from './mesa';

export class EditarSalonDto {
  id: number;
  name: string;
  tableroDeMesas: Mesa[][];

  constructor(id: number, name: string, tableroDeMesas: Mesa[][]) {
    this.id = id;
    this.name = name;
    this.tableroDeMesas = tableroDeMesas;
  }
}
