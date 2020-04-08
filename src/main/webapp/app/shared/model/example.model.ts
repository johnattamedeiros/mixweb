export interface IExample {
  id?: number;
  name?: string;
  age?: number;
}

export class Example implements IExample {
  constructor(public id?: number, public name?: string, public age?: number) {}
}
