import { Message } from "./message";
import { RecordName } from "./recordName";

export interface Rate {
    id: number;
    match: RecordName;
    player: RecordName;
    rate: number;
}
