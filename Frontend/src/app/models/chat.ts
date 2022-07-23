import { Message } from "./message";
import { RecordName } from "./recordName";

export interface Chat {
    id: number;
    name: string;
    messages: Message[];
}
