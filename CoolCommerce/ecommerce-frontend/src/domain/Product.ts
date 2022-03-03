import Category from '@/domain/Category'

/**
 * Product information
 */
export default class Product {

    public id: number

    public name!: string

    public description?: string

    public price!: number

    public imageThumbnailUrl!: string

    public imageUrl!: string

    public sourceUrl!: string

    public category!: Category

    constructor(id: number) {
        this.id = id
    }
}