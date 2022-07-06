export interface EntityMedia {
    mainMedia: string;
    media: Media;
}

export interface Media {
    id: number;
    url: string;
    isMain: boolean;
}