import { RecordName } from "./recordName";

export interface Message {
    user: RecordName;
    content: string;
    time: string;
}
