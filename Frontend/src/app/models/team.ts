import { EntityMedia } from "./media";
import { RecordName } from "./recordName";

export class Team {
    id: number;
    name: string;
    dateOfEstablishment: Date;
    president: RecordName;
    coach: RecordName;
    stadium: RecordName;
    media: EntityMedia;
}