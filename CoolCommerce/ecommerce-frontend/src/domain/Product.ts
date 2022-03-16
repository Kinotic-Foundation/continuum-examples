import Category from '@/domain/Category'
import Image from '@/domain/Image'

/**
 * Product information
 */
export default class Product {

    public id: number

    public name!: string

    public description?: string

    public price!: number

    public rating!: number

    public thumbnailImage: Image = new Image()

    public image: Image = new Image()

    public category!: Category

    constructor(id: number) {
        this.id = id
    }
}