

export class Role {

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get role(): string {
    return this._role;
  }

  set role(value: string) {
    this._role = value;
  }

  private _id: number;
  private _role: string;

  // public getRole(): string {
  //   return this._role;
  // }
  // public setRole(value: string): void {
  //   this._role = value;
  // }
}
