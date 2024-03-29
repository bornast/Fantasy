import { EntityMedia } from "./media";
import { RecordName } from "./recordName";

export class Player {
    id: number;
    name: string;
    dateOfBirth: Date;
    position: RecordName;
    media: EntityMedia;
    matchesPlayer: number;
    goals: number;
    yellowCards: number;
    redCards: number;
    rate: number;
}
