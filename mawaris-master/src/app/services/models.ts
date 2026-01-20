export interface InheritanceRequest {
  totalEstate: number;
  debts: number;
  will: number;
  heirs: {
    [key: string]: number;
  };
}

export interface InheritanceShare {
  heirType: string;
  nasib: string;
  countMembers: number;
  nisbtElfard: string;
  individualAmount: number;
  reason: string;
}

export interface FullInheritanceResponse {
  title: string;
  note: string;
  shares: InheritanceShare[];
}

export interface HeirResult {
  heirType: string;
  nasib: string;
  countMembers: number;
  nisbtElfard: string;
  individualAmount: number;
  reason: string;
}

export interface CalculationDetails {
  title?: string;
  note?: string;
  totalEstate?: number;
  netEstate?: number;
  debts?: number;
  willAmount?: number;
}
