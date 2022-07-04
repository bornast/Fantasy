import { RecordName } from "./recordName";

export interface User {
    id: number;
    username: string;
    firstName: string;
    lastName: string;
    roles: RecordName[];
}
