import { EntityMedia } from "./media";
import { RecordName } from "./recordName";

export class Transfer {
    id: number;
    player: RecordName;
    fromTeam: RecordName;
    toTeam: RecordName;
    transferDate: Date;
}